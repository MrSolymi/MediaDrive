<script lang="ts">
	import { Button } from '$lib/components/ui/button/index.js';
	import * as Card from '$lib/components/ui/card/index.js';
	import { Input } from '$lib/components/ui/input/index.js';
	import { Label } from '$lib/components/ui/label/index.js';
</script>

<div class="flex h-screen w-full items-center justify-center px-4">
	<Card.Root class="mx-auto max-w-sm">
		<Card.Header>
			<Card.Title class="text-2xl">Registration</Card.Title>
			<Card.Description>Enter your inivte token below to register</Card.Description>
		</Card.Header>
		<Card.Content>
			<div class="grid gap-4">
				<div class="grid gap-2">
					<Label for="email">Invite token</Label>
					<Input id="email" type="email" placeholder="example token" required />
				</div>
				<Button type="submit" class="w-full">Register</Button>
			</div>
			<div class="mt-4 text-center text-sm">
				Have an account?
				<a href="/login" class="underline"> Sign In </a>
			</div>
		</Card.Content>
	</Card.Root>
</div>
